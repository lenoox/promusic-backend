INSERT INTO public.brands (brand_name) VALUES ('TSPV') ON CONFLICT DO NOTHING;
INSERT INTO public.brands (brand_name) VALUES ('DHQForMusic') ON CONFLICT DO NOTHING;
INSERT INTO public.brands (brand_name) VALUES ('UFreshSound') ON CONFLICT DO NOTHING;
INSERT INTO public.brands (brand_name) VALUES ('PRTU-X') ON CONFLICT DO NOTHING;
INSERT INTO public.brands (brand_name) VALUES ('YourHighSoundXK') ON CONFLICT DO NOTHING;
INSERT INTO public.order_status (status_name) VALUES ('Nowy') ON CONFLICT DO NOTHING;
INSERT INTO public.order_status (status_name) VALUES ('Opłacony') ON CONFLICT DO NOTHING;
INSERT INTO public.order_status (status_name) VALUES ('Wysłany') ON CONFLICT DO NOTHING;